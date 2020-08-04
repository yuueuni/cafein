export default{
    URL: 'http://i3a203.p.ssafy.io:5000/api',
    ROUTES: {
        //accounts 
        signup: '/user/signup',
        login: '/user/login',
        mypage: '/user',

        //post
        postList: '/post/list/cafe/1/',
        postDetail: '/post',
        postDelete: '/post/delete/',
        UploadImage: '/post/upload',

        //comment
        createComment: '/comment',
        commentList: '/comment/list/',
        deleteComment: '/comment/delete/',

        //like, stamp
        like: '/like',
        stamp: '/stamp',

        //follow
        follow: '/follow',
        
        // cafe
        cafeDetail: '/cafe/', // + :id
        cafeList: '/cafe/list/'


    }
}