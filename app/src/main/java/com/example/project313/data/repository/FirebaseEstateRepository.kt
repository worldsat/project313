package com.example.project313.data.repository

import android.util.Log
import com.example.project313.R
import com.example.project313.data.mock.MockData
import com.example.project313.data.model.Advisor
import com.example.project313.data.model.Estate
import com.example.project313.data.model.FilterCategory
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirebaseEstateRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    companion object {
        private const val TAG = "FirebaseEstateRepo"
        const val COLLECTION_ESTATES = "estates"

        @Volatile
        private var instance: FirebaseEstateRepository? = null

        fun getInstance(): FirebaseEstateRepository {
            return instance ?: synchronized(this) {
                instance ?: FirebaseEstateRepository().also { instance = it }
            }
        }
    }

    /**
     * Real-time Flow of estates from Firebase Firestore "estates" collection.
     * Automatically falls back to MockData if collection is empty, offline, or unavailable.
     */
    fun getEstatesFlow(): Flow<List<Estate>> = callbackFlow {
        try {
            val listener = firestore.collection(COLLECTION_ESTATES)
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        Log.w(TAG, "Firestore listen encountered error, falling back to MockData: ${error.message}")
                        trySend(getAllMockEstates())
                        return@addSnapshotListener
                    }

                    if (snapshot != null && !snapshot.isEmpty) {
                        val estates = snapshot.documents.mapNotNull { doc ->
                            docToEstate(doc.id, doc.data ?: emptyMap())
                        }
                        Log.d(TAG, "Fetched ${estates.size} estates from Firebase Firestore")
                        trySend(estates)
                    } else {
                        Log.d(TAG, "Firestore 'estates' collection is empty, seeding mock data...")
                        trySend(getAllMockEstates())
                        seedMockDataIfEmpty()
                    }
                }

            awaitClose { listener.remove() }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize Firestore listener: ${e.message}")
            trySend(getAllMockEstates())
            awaitClose { }
        }
    }

    /**
     * Seeds initial mock data into Firestore if not yet populated.
     */
    fun seedMockDataIfEmpty() {
        val allEstates = getAllMockEstates()

        val batch = firestore.batch()
        for (estate in allEstates) {
            val docRef = firestore.collection(COLLECTION_ESTATES).document(estate.id)
            val data = estateToMap(estate)
            batch.set(docRef, data)
        }

        batch.commit()
            .addOnSuccessListener {
                Log.i(TAG, "Successfully seeded ${allEstates.size} estates to Firestore")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Could not seed estates to Firestore (database may need creation): ${e.message}")
            }
    }

    /**
     * Toggles the saved status of an estate in Firestore.
     */
    fun toggleSaveEstate(estateId: String, isSaved: Boolean) {
        firestore.collection(COLLECTION_ESTATES).document(estateId)
            .update("isSaved", isSaved)
            .addOnFailureListener { e ->
                Log.w(TAG, "Failed to update save status in Firestore: ${e.message}")
            }
    }

    private fun getAllMockEstates(): List<Estate> {
        return listOf(
            MockData.featuredEstate,
            MockData.popularEstates[0],
            MockData.popularEstates[1],
            MockData.popularEstates[2],
            MockData.popularEstates[3]
        )
    }

    private fun docToEstate(id: String, data: Map<String, Any>): Estate {
        val imageKey = data["imageKey"] as? String ?: id
        val imageResId = when (imageKey) {
            "architectural_photograph_of_the_glass", "the-glass-pavilions" -> R.drawable.architectural_photograph_of_the_glass
            "emerald_grove", "emerald-grove-residence" -> R.drawable.emerald_grove
            "modern_luxury", "the-cedar-ridge-villa" -> R.drawable.modern_luxury
            "botanica_luxury", "skyline-botanica-penthouse" -> R.drawable.botanica_luxury
            "sanctuary_cottage_in", "oakwood-sanctuary-cottage" -> R.drawable.sanctuary_cottage_in
            else -> R.drawable.architectural_photograph_of_the_glass
        }

        val categoryStr = data["category"] as? String ?: "VILLAS"
        val category = try {
            FilterCategory.valueOf(categoryStr.uppercase())
        } catch (_: Exception) {
            FilterCategory.VILLAS
        }

        val advisorName = data["advisorName"] as? String ?: "Sarah Jenkins"
        val advisorTitle = data["advisorTitle"] as? String ?: "Principal Private Advisor"
        val advisorDivision = data["advisorDivision"] as? String ?: "Austin Luxury Division"

        val advisor = Advisor(
            name = advisorName,
            title = advisorTitle,
            division = advisorDivision,
            avatarResId = R.drawable.agent
        )

        val priceNumber = (data["price"] as? Number)?.toLong() ?: 3850000L
        val bedsNumber = (data["beds"] as? Number)?.toInt()
        val bathsNumber = (data["baths"] as? Number)?.toInt()
        val sqftNumber = (data["sqft"] as? Number)?.toInt()
        val ratingNumber = (data["rating"] as? Number)?.toFloat()
        val reviewCountNumber = (data["reviewCount"] as? Number)?.toInt()
        val photoCountNumber = (data["photoCount"] as? Number)?.toInt() ?: 4

        return Estate(
            id = id,
            title = data["title"] as? String ?: "",
            price = priceNumber,
            formattedPrice = data["formattedPrice"] as? String ?: "$${priceNumber}",
            estimatedMonthly = data["estimatedMonthly"] as? String ?: "",
            address = data["address"] as? String ?: "",
            beds = bedsNumber,
            baths = bathsNumber,
            sqft = sqftNumber,
            rating = ratingNumber,
            reviewCount = reviewCountNumber,
            imageResId = imageResId,
            statusTag = data["statusTag"] as? String,
            areaTag = data["areaTag"] as? String,
            verifiedLuxury = data["verifiedLuxury"] as? Boolean ?: false,
            isFeatured = data["isFeatured"] as? Boolean ?: false,
            category = category,
            description = data["description"] as? String,
            advisor = advisor,
            acreage = data["acreage"] as? String,
            leedRating = data["leedRating"] as? String,
            narrative = data["narrative"] as? String,
            originalPrice = data["originalPrice"] as? String,
            priceDropNotice = data["priceDropNotice"] as? String,
            openHouseNotice = data["openHouseNotice"] as? String,
            availabilityTag = data["availabilityTag"] as? String,
            terraceSpec = data["terraceSpec"] as? String,
            isUnderContract = data["isUnderContract"] as? Boolean ?: false,
            photoCount = photoCountNumber,
            activePhotoIndex = 0
        )
    }

    private fun estateToMap(estate: Estate): Map<String, Any?> {
        val imageKey = when (estate.imageResId) {
            R.drawable.architectural_photograph_of_the_glass -> "architectural_photograph_of_the_glass"
            R.drawable.emerald_grove -> "emerald_grove"
            R.drawable.modern_luxury -> "modern_luxury"
            R.drawable.botanica_luxury -> "botanica_luxury"
            R.drawable.sanctuary_cottage_in -> "sanctuary_cottage_in"
            else -> "architectural_photograph_of_the_glass"
        }

        return mapOf(
            "id" to estate.id,
            "title" to estate.title,
            "price" to estate.price,
            "formattedPrice" to estate.formattedPrice,
            "originalPrice" to estate.originalPrice,
            "estimatedMonthly" to estate.estimatedMonthly,
            "address" to estate.address,
            "beds" to estate.beds,
            "baths" to estate.baths,
            "sqft" to estate.sqft,
            "rating" to estate.rating,
            "reviewCount" to estate.reviewCount,
            "imageKey" to imageKey,
            "statusTag" to estate.statusTag,
            "areaTag" to estate.areaTag,
            "verifiedLuxury" to estate.verifiedLuxury,
            "isFeatured" to estate.isFeatured,
            "category" to estate.category.name,
            "description" to estate.description,
            "acreage" to estate.acreage,
            "leedRating" to estate.leedRating,
            "narrative" to estate.narrative,
            "priceDropNotice" to estate.priceDropNotice,
            "openHouseNotice" to estate.openHouseNotice,
            "availabilityTag" to estate.availabilityTag,
            "terraceSpec" to estate.terraceSpec,
            "isUnderContract" to estate.isUnderContract,
            "photoCount" to estate.photoCount,
            "advisorName" to (estate.advisor?.name ?: "Sarah Jenkins"),
            "advisorTitle" to (estate.advisor?.title ?: "Principal Private Advisor"),
            "advisorDivision" to (estate.advisor?.division ?: "Austin Luxury Division")
        )
    }
}
