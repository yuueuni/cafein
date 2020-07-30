export default{
    URL: 'http://i3a203.p.ssafy.io:5000/api',
    ROUTES: {
        //accounts 
        signup: '/user/signup/',
        login: '/user/login',
        logout: '',
        mypage: '/user/',

        //post
        postList: '/post/list/cafe/1/',
        postDetail: '/post/',
        UploadImage: '/post/upload',

        //comment
        createComment: '',
        commentList: '',
        deleteComment: '',

        //like, stamp
        like: '/like/',
        stamp: '',

        //follow
        following: '',
        folower: '',
        
        // cafe
        cafeDetail: '/cafe/', // + :id
        cafeList: '/cafe/list/1'


    }
}