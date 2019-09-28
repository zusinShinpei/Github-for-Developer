package com.seok.gitfordeveloper.retrofit.request

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class TRCommitResponseDto {
    var id : Long = 0

    var userId : String = ""

    var dataDate : String = ""

    var dataCount : String = ""

    constructor(id : Long, userId : String, dataCount : String, dataDate : String){
        this.id = id
        this.userId = userId
        this.dataDate = dataDate
        this.dataCount = dataCount
    }
}