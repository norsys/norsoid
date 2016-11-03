package fr.norsys.android.norsoid.sample.service;

import fr.norsys.android.norsoid.sample.model.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostService {

    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") String id);

}
