package com.pramodbharti.filmo.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class CreditsResponse(
    val cast:List<CastResponse>
)
