package com.example.project313.data.mock

import com.example.project313.R
import com.example.project313.data.model.Advisor
import com.example.project313.data.model.Estate
import com.example.project313.data.model.FilterCategory

object MockData {
    val sarahJenkins = Advisor(
        name = "Sarah Jenkins",
        title = "Principal Private Advisor",
        division = "Austin Luxury Division",
        avatarResId = R.drawable.agent
    )

    val featuredEstate = Estate(
        id = "the-glass-pavilions",
        title = "The Glass Pavilions, Westlake",
        price = 3850000L,
        formattedPrice = "$3,850,000",
        estimatedMonthly = "Est. $18,420/mo",
        address = "4420 Scenic Bluff Way, Westlake Hills",
        beds = 5,
        baths = 6,
        sqft = 5400,
        rating = 4.95f,
        reviewCount = 38,
        imageResId = R.drawable.architectural_photograph_of_the_glass,
        statusTag = "Exclusive Reserve",
        areaTag = "Westlake Hills",
        verifiedLuxury = true,
        isFeatured = true,
        category = FilterCategory.VILLAS,
        description = "A masterclass in modern transparency and limestone craftsmanship perched high above Lake Austin.",
        advisor = sarahJenkins,
        acreage = "1.8 Acres",
        leedRating = "LEED Platinum",
        narrative = "Perched dramatically above Lake Austin, The Glass Pavilions embodies sustainable luxury through structural purity and environmental harmony. Characterized by expansive cantilevered glass volumes, rammed-earth accents, and locally quarried Westlake limestone, this sanctuary integrates seamless indoor-outdoor living with native drought-tolerant gardens and a negative-edge reflection pool.",
        photoCount = 4,
        activePhotoIndex = 0
    )

    val popularEstates = listOf(
        Estate(
            id = "emerald-grove-residence",
            title = "Emerald Grove Residence",
            price = 2450000L,
            formattedPrice = "$2,450,000",
            estimatedMonthly = "Est. $11,200/mo",
            address = "7804 Spyglass Peak, Austin, TX 78746",
            beds = 4,
            baths = 4,
            sqft = 3900,
            imageResId = R.drawable.emerald_grove,
            statusTag = "For Sale",
            areaTag = "Austin Greenbelt",
            category = FilterCategory.VILLAS,
            description = "Nestled alongside Barton Creek preserve, featuring terraced living, native flora, and infinity-edge reflection pool.",
            advisor = sarahJenkins,
            photoCount = 4,
            activePhotoIndex = 0
        ),
        Estate(
            id = "the-cedar-ridge-villa",
            title = "The Cedar Ridge Villa",
            price = 1890000L,
            formattedPrice = "$1,890,000",
            estimatedMonthly = "Est. $8,660/mo",
            address = "1204 Sunset View Terrace, Travis Heights",
            beds = 3,
            baths = 3,
            sqft = 2850,
            imageResId = R.drawable.modern_luxury,
            statusTag = "New Listing",
            areaTag = "Travis Heights",
            category = FilterCategory.VILLAS,
            description = "Warm western red cedar craftsmanship coupled with architectural standing seam steel roof in vibrant Travis Heights.",
            advisor = sarahJenkins,
            photoCount = 4,
            activePhotoIndex = 0
        ),
        Estate(
            id = "skyline-botanica-penthouse",
            title = "Skyline Botanica Penthouse",
            price = 4200000L,
            formattedPrice = "$4,200,000",
            estimatedMonthly = "Est. $19,800/mo",
            address = "200 Congress Avenue #3801, Downtown",
            beds = 4,
            baths = 5,
            sqft = 4800,
            imageResId = R.drawable.botanica_luxury,
            statusTag = "For Sale",
            areaTag = "Downtown Austin",
            category = FilterCategory.PENTHOUSES,
            description = "Panoramic sunset vistas over Lady Bird Lake and the downtown skyline from a multi-level botanical terrace.",
            advisor = sarahJenkins,
            photoCount = 5,
            activePhotoIndex = 0
        ),
        Estate(
            id = "oakwood-sanctuary-cottage",
            title = "Oakwood Sanctuary Cottage",
            price = 1650000L,
            formattedPrice = "$1,650,000",
            estimatedMonthly = "Est. $7,400/mo",
            address = "2206 Exposition Blvd, Tarrytown",
            beds = 3,
            baths = 2,
            sqft = 2200,
            imageResId = R.drawable.sanctuary_cottage_in,
            statusTag = "For Sale",
            areaTag = "Tarrytown",
            category = FilterCategory.ECO_HOMES,
            description = "Preserved heritage live oaks frame this restored Austin limestone retreat with modern eco-certified retrofits.",
            advisor = sarahJenkins,
            photoCount = 3,
            activePhotoIndex = 0
        )
    )

    val savedEstates = listOf(
        Estate(
            id = "the-glass-pavilions",
            title = "The Glass Pavilions",
            price = 3850000L,
            formattedPrice = "$3,850,000",
            originalPrice = "$3,900,000",
            priceDropNotice = "↓ -$50,000 this week",
            openHouseNotice = "Open Sat 2:00 PM",
            estimatedMonthly = "Est. $18,420/mo",
            address = "Westlake Hills, Austin, TX",
            beds = 5,
            baths = 6,
            sqft = 5400,
            imageResId = R.drawable.architectural_photograph_of_the_glass,
            verifiedLuxury = true,
            isFeatured = true,
            category = FilterCategory.VILLAS,
            advisor = sarahJenkins
        ),
        Estate(
            id = "emerald-grove-residence",
            title = "Emerald Grove Residence",
            price = 2450000L,
            formattedPrice = "$2,450,000",
            availabilityTag = "Available",
            estimatedMonthly = "Est. $11,200/mo",
            address = "Barton Creek Preserve, Austin, TX",
            beds = 4,
            baths = 4,
            sqft = 4120,
            imageResId = R.drawable.emerald_grove,
            category = FilterCategory.VILLAS,
            advisor = sarahJenkins
        ),
        Estate(
            id = "skyline-botanica-penthouse",
            title = "Skyline Botanica Penthouse",
            price = 4200000L,
            formattedPrice = "$4,200,000",
            availabilityTag = "Under Contract",
            isUnderContract = true,
            estimatedMonthly = "Est. $19,800/mo",
            address = "Rainey District, Downtown Austin, TX",
            beds = 4,
            baths = 5,
            terraceSpec = "Terrace",
            imageResId = R.drawable.botanica_luxury,
            category = FilterCategory.PENTHOUSES,
            advisor = sarahJenkins
        ),
        Estate(
            id = "oakwood-sanctuary-cottage",
            title = "Oakwood Sanctuary Cottage",
            price = 1150000L,
            formattedPrice = "$1,150,000",
            availabilityTag = "New Price",
            estimatedMonthly = "Est. $5,400/mo",
            address = "Tarrytown, Austin, TX",
            beds = 3,
            baths = 2,
            acreage = "0.45 Acres",
            imageResId = R.drawable.sanctuary_cottage_in,
            category = FilterCategory.ECO_HOMES,
            advisor = sarahJenkins
        )
    )

    fun getEstateById(id: String): Estate {
        if (id == featuredEstate.id) return featuredEstate
        savedEstates.find { it.id == id }?.let { return it }
        return popularEstates.find { it.id == id } ?: featuredEstate
    }
}
