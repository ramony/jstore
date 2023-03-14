rem set expired time https://blog.csdn.net/weixin_37665711/article/details/119254622
@kubectl -n kubernetes-dashboard create token admin-user --duration=0s
@kubectl proxy