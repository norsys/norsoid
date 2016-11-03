package fr.norsys.android.norsoid.sample.manager.impl;

import fr.norsys.android.norsoid.sample.manager.PostManager;
import fr.norsys.android.norsoid.sample.post.Post;
import fr.norsys.android.norsoid.sample.service.PostService;
import fr.norsys.android.norsoid.service.ServiceCallBack;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostManagerImpl implements PostManager {

    private PostService mPostService;

    public PostManagerImpl(PostService postService) {
        mPostService = postService;
    }

    @Override
    public void getPost(String id, final ServiceCallBack<Post> serviceCallBack) {
        mPostService.getPost(id).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                serviceCallBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                serviceCallBack.onError(t);
            }
        });
    }
}
