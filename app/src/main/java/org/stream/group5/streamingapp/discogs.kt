package org.stream.group5.streamingapp

import android.app.AlertDialog
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import saschpe.discogs.Discogs
import saschpe.discogs.model.database.Result
import saschpe.discogs.model.database.Search
import saschpe.discogs.service.DatabaseService

// needs key and secret set

val discogs = Discogs("MusicStreamingApp/2.0", key = key , secret = secret)

fun srch(name: String, artist: String, activity: org.stream.group5.streamingapp.MainActivity): Unit {
    // Search the Discogs database for album / artist...
    // var data: String = "test"

    val newB : AlertDialog.Builder = AlertDialog.Builder(activity);
    newB.setTitle("Discogs Results")
    newB.create()

    discogs.database
            .search(hashMapOf(
                    DatabaseService.SEARCH_ARTIST to artist,
                    DatabaseService.SEARCH_TRACK to name))
            .enqueue(object : Callback<Search> {
                override fun onFailure(call: Call<Search>, t: Throwable) {
                }

                override fun onResponse(call: Call<Search>, response: Response<Search>) {
                    if (response.isSuccessful()) {
                        val resp = response.body()
                        val results: List<Result>? = resp?.results

                        var lst: MutableList<String> = mutableListOf()

                        for (e in results!!.iterator()) {
                            lst.add(e.title.toString())
                        }

                        Log.d("TEST2", lst.toString())

                        newB.setItems(lst.toTypedArray()) {dialog, items ->
                            Log.d("TEST2", "Clickedme")
                            // This is where you would edit the tags for the file on press, but did not get there
                            dialog.dismiss()
                        }
                        newB.setPositiveButton("Back") {dialog, _ ->
                            dialog.dismiss()
                        }
                        newB.show()

                    }
                    else {
                        Log.d("TEST2", "testing")
                    }
                }})

}
