package fr.norsys.android.norsoid.sample.manager;

import fr.norsys.android.norsoid.sample.post.Post;
import fr.norsys.android.norsoid.service.ServiceCallBack;

public interface PostManager {

    void getPost(String id, ServiceCallBack<Post> serviceCallBack);
}
