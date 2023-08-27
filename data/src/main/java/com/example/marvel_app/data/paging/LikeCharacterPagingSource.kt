package com.example.marvel_app.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvel_app.data.db.CharacterDao
import com.example.marvel_app.domain.model.MarvelCharacter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class LikeCharacterPagingSource(
    private val characterDao: CharacterDao,
) :
    PagingSource<Int, MarvelCharacter>() {

    override fun getRefreshKey(state: PagingState<Int, MarvelCharacter>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarvelCharacter> {
        val start = params.key ?: 1
        return try {
            var data: List<MarvelCharacter>? = null

            CoroutineScope(Dispatchers.IO).launch {
                data = characterDao.getAllCharacters(start)
            }.join()

            LoadResult.Page(
                data = data!!,
                prevKey = if (start == 1) null else start-1,
                nextKey = if(data.isNullOrEmpty()) null else start+1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
