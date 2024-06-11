package com.melisa.paradise.data.domain

class AdoptPet{
    var name:String=""
    var type:String=""
    var id:String=""
    var location:String=""

   constructor(name:String,type:String,location:String,id:String){
       this.name=name
       this.type=type
       this.location=location
       this.id=id

   }
    constructor()
}
