package com.melisa.paradise.viewmodels

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase
import com.melisa.paradise.data.domain.AdoptPet

class AdoptPetViewModel(var navController: NavController, var context: Context) {
     var progress: ProgressDialog

    init {
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }


    fun adoptPet(name:String,type: String,location: String) {
        var id = System.currentTimeMillis().toString()
        var petData = AdoptPet(name,type,location, id)
        var petRef = FirebaseDatabase.getInstance().getReference()
            .child("Dog/$id")
        progress.show()
        petRef.setValue(petData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Adoption request successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


    fun adoptionCancelled(id: String) {
        var delRef = FirebaseDatabase.getInstance().getReference()
            .child("AdoptPet/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Pet Adption Cancelled", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

