package com.example.ska23.spotifyclient

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.spotifywebapi.SpotifyWebService
import com.example.spotifywebapi.models.Playlist
import com.example.spotifywebapi.models.PlaylistResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.playlistRecyclerView
import kotlinx.android.synthetic.main.playlist_item_viewholder.view.playlist_item_imageview
import kotlinx.android.synthetic.main.playlist_item_viewholder.view.playlist_item_title
import retrofit2.adapter.rxjava2.Result


class SpotifyExampleActivity : SpotifyBaseActivity() {


    override var REDIRECT_URI : String? = "things-spotify-client://login/callback"
    override var CLIENT_ID: String? = "c1e596724b3442fda53060bf6f75ec86"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(token == null) {
            SpotifyAuthManager.authenticateUser(this,
                    CLIENT_ID.orEmpty(),
                    REDIRECT_URI.orEmpty(),
                    REQUEST_CODE,
                    arrayOf(ScopeConsts.USER_READ_PRIVATE,
                            ScopeConsts.STREAMING,
                            ScopeConsts.PLAYLIST_READ_PRIVATE,
                            ScopeConsts.PLAYLIST_READ_COLLABORATIVE))
        }
    }

    override fun onResume() {
        super.onResume()

        token?.let{
            SpotifyWebService().getMyPlayLists(it)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ t: Result<PlaylistResponse> ->
                        if(t.isError) {

                        } else {
                            val list = t.response()?.body()?.items.orEmpty()

                            playlistRecyclerView.adapter = PlayListAdapter(list)
                            playlistRecyclerView.layoutManager = LinearLayoutManager(this)
                        }

                    }, { error ->
                        Log.e("error",error.stackTrace.toString())
                    })
        }
    }

}

class PlayListAdapter(private val items: List<Playlist.Simplified>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.playlist_item_viewholder, parent, false)
        return PlaylistViewHolder(view)
    }

    override fun getItemCount()= items.count()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(holder is PlaylistViewHolder) {
            holder.view.playlist_item_title.text = items[position].name
            Glide.with(holder.view.context)
                    .load(items[position].images[0].url)
                    .apply(RequestOptions().override(300,200))
                    .into(holder.view.playlist_item_imageview)

        }
    }

}

class PlaylistViewHolder(val view: View) : RecyclerView.ViewHolder(view)
