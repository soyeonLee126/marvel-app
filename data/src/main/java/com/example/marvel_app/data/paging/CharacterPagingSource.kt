package com.example.marvel_app.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvel_app.data.BuildConfig
import com.example.marvel_app.data.repository.dataSource.CharacterRemoteDataSource
import com.example.marvel_app.domain.model.MarvelCharacter
import com.example.marvel_app.util.Constants.LOAD_SIZE
import com.example.marvel_app.util.Constants.STARTING_OFFSET
import retrofit2.HttpException
import java.io.IOException
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class CharacterPagingSource(
    private val characterDataSource: CharacterRemoteDataSource,
) : PagingSource<Int, MarvelCharacter>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarvelCharacter> {
        return try {
            val page = params.key ?: STARTING_OFFSET
            val offset = page * LOAD_SIZE

            val ts = Timestamp(System.currentTimeMillis()).time.toString()
            val hash = md5("$ts${BuildConfig.API_KEY_PRIVATE}${BuildConfig.API_KEY}")

            val response = characterDataSource.getAllCharacters(offset=offset, ts=ts, apiKey=BuildConfig.API_KEY, hash=hash)

            val characters = response.body()?.data?.results?.toMutableList() ?: emptyList()
            val nextPage = if (offset >= response.body()?.data?.total!!) null else page + 1

            LoadResult.Page(
                data = characters,
                prevKey = null,
                nextKey = nextPage
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(
        state: PagingState<Int, MarvelCharacter>
    ): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(
                anchorPosition
            )
            anchorPage?.prevKey?.plus(1) ?:
            anchorPage?.nextKey?.minus(1)
        }
    }

    fun md5(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32 ,'0')
    }
}
