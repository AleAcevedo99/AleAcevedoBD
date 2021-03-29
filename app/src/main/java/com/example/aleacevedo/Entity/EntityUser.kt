package com.example.aleacevedo.Entity

data class EntityUser(
    var id: Long,
    var email:String,
    var password:String,
    var phoneNumber:String,
    var gender:Int){
    constructor():this(0,"", "", "", 0)
}