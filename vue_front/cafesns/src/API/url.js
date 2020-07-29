export default{
    URL: 'https://i3a203.p.ssafy.io:5000',
    ROUTES: {
        //accounts 
        signup: '/api/user/signup/',
        login: '/api/user/login',
        logout: '',
        mypage: '/api/user/',

        //post
        postList: '/api/post',
        UploadImage: '/api/post/upload',

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
        cafeDetail: '/api/cafe/', // + :id
        cafeList: '/api/cafe/list/1'


    }
}