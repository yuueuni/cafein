export default{
    URL: 'http://i3a203.p.ssafy.io:5000/api',
    ROUTES: {
        //accounts 
        signup: '/user/signup',
        login: '/user/login',
        mypage: '/user',

        //post
        postList: '/post',
        UploadImage: '/post/upload',

        //comment
        createComment: '',
        commentList: '',
        deleteComment: '',

        //like, stamp
        like: '/like',
        stamp: '/stamp',

        //follow
        follow: '/follow',
        
        // cafe
        cafeDetail: '/cafe/', // + :id
        cafeList: '/cafe/list/1'


    }
}