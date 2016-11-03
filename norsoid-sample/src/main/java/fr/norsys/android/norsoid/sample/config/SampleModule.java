package fr.norsys.android.norsoid.sample.config;

import dagger.Module;
import dagger.Provides;
import fr.norsys.android.norsoid.sample.manager.PostManager;
import fr.norsys.android.norsoid.sample.manager.impl.PostManagerImpl;
import fr.norsys.android.norsoid.sample.service.PostService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class SampleModule {

    @Provides
    public PostService providePostService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(PostService.class);
    }

    @Provides
    public PostManager providePostManager(PostService postService) {
        return new PostManagerImpl(postService);
    }
}
