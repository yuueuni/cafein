<template>
  <div class="home">
    <!-- <template v-if="!isAuthenticated"> -->
    <!-- </template> -->
    <!-- <template v-else> -->
      <div v-for="(item, index) in posts" :key="index" class="container">
        <article class="bg-white border mb-5 mt-5">
          <div class="d-flex justify-content-between border-bottom">
            <h5 class="display-5 width100 m-3" for="phone">{{ item.uid }}</h5>
            <input
              v-if="item.uid == user.id"
              type="button"
              class="btn btn-link text-danger"
              value="게시글 삭제"
              @click="removePost(item, index)"
            />
          </div>
          <div>
            <div class="border-bottom border-top">
              <div id="heart" class="mx-3 my-0">
                <button id="like" @click="like(item.pno, index)">
                </button>
                <span class="lcnt font-weight-bold mt-1"
                  >{{ item.like }}개</span
                >
              </div>
            </div>
            <div class="px-3 border-bottom mt-3">
              <p>{{ item.contents }}</p>
              <p>{{ item.rentType }}: {{ item.rentPrice }}원</p>
              <p>등록일: {{ item.date }}</p>
              <p>동읍면: {{ item.dong }}</p>
            </div>
            <div class="px-3 pt-2">
              <h5>댓글</h5>
            </div>
            <div
              v-for="(data, idx) in item.comments"
              v-bind:key="idx"
              class="input-group px-3"
            >
              <span class="input-group-btn py-2 text-primary">
                {{ data.uid }}
              </span>
              <input
                type="text"
                class="form-control width100"
                v-model="data.contents"
                :readonly="data.uid !== user.id"
                @keyup.enter="updateComment(data, index)"
              />
              <template v-if="data.uid == user.id">
                <span class="input-group-btn">
                  <button
                    type="button"
                    class="btn btn-link text-warning mx-2"
                    @click="updateComment(data, index)"
                  >
                    수정
                  </button>
                </span>
                <span class="input-group-btn">
                  <button
                    type="button"
                    class="btn btn-link text-danger"
                    @click="removeComment(data.cno, index)"
                  >
                    삭제
                  </button>
                </span>
              </template>
            </div>
            <div class="border-top input-group p-2">
              <input
                type="text"
                class="form-control width100"
                placeholder="댓글 달기..."
                v-model="item.msg"
                @keyup.enter="addComment(item.pno, item.msg, index)"
              />

              <span class="input-group-btn">
                <button
                  type="button"
                  class="btn btn-link text-info font-weight-bold py-2 px-2"
                  @click="addComment(item.pno, posts[index].msg, index)"
                >
                  게시
                </button>
              </span>
            </div>
          </div>
        </article>
      </div>

      <infinite-loading
        @infinite="infiniteHandler"
        spinner="waveDots"
      ></infinite-loading>
    <!-- </template> -->
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import axios from "axios";

// const api = "http://localhost:8080/post/list";

export default {
  name: "Home",
  computed: {
    ...mapGetters(["isAuthenticated", "user"])
  },
  data() {
    return {
      page: 1,
      posts: []
    };
  },
  methods: {
    removePost(item, index) {
      if (!confirm("삭제하시겠습니까?")) {
        return;
      }
      console.log("index: " + index);
      this.posts.splice(index, 1);

      axios
        .delete(`http://localhost:8080/post/${item.pno}`)
        .then(() => {
          console.log("success");
        })
        .catch(err => {
          console.log(err);
        });
    },
    updateComment(data, i) {
      console.log("update comment: " + data.cno + " " + i);
      console.log(data.contents);
      console.log(this.user.id);
      const payload = {
        cno: data.cno,
        uid: this.user.id,
        contents: data.contents,
        pno: data.pno
      };
      axios
        .put(`http://localhost:8080/comment`, payload)
        .then(() => {
          console.log("success");
        })
        .catch(err => {
          console.log(err);
        });
    },
    like(pno, i) {
      console.log("like");
      console.log(this.user.id + " " + pno);
      const uid = this.user.id;
      axios
        .get(`http://localhost:8080/like/${pno}/${uid}`)
        .then(resp => {
          console.log("-----");
          console.log(resp);
          return resp;
        })
        .then(resp => {
          console.log("COUNT: " + resp.data);
          if (resp.data === 0) {
            const data = {
              pno: pno,
              uid: uid
            };
            axios
              .post(`http://localhost:8080/like`, data)
              .then(() => {
                console.log(this.posts, this.posts[i])
                this.$set(this.posts[i], "like", this.posts[i].like + 1);
                console.log("success");
              })
              .catch(err => {
                console.log(err);
              });
          } else {
            axios
              .delete(`http://localhost:8080/like/${pno}/${this.user.id}`)
              .then(() => {
                console.log("success");
                this.$set(this.posts[i], "like", this.posts[i].like - 1);
              })
              .catch(err => {
                console.log(err);
              });
            console.log("else");
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    removeComment(cno, i) {
      console.log("remove comment: " + cno);
      // console.log(this.posts[i]);
      const comments = this.posts[i].comments.filter(function(cmt) {
        return cmt.cno !== cno;
      });
      axios
        .delete(`http://localhost:8080/comment/${cno}`)
        .then(() => {
          this.$set(this.posts[i], "comments", comments);
        })
        .catch(err => {
          console.log(err);
        });
    },
    addComment(pno, contents, i) {
      if (contents.trim().length == 0) {
        return;
      }

      const data = {
        pno: pno,
        contents: contents.trim(),
        uid: this.user.id
      };

      axios
        .post(`http://localhost:8080/comment`, data)
        .then(resp => {
          // console.log("========");
          // console.log(resp);
          data.cno = resp.data;
          this.posts[i].comments.push(data);
          this.$set(this.posts[i], "msg", "");
        })
        .catch(err => {
          console.log(err);
        });
    },
    infiniteHandler($state) {
      axios
        // .get(`${api}/${this.page}`)
        .then(({ data }) => {
          let promises = [];
          for (let i = 0; i < data.length; i++) {
            promises.push(
              axios
                .get(`http://localhost:8080/comment/list/${data[i].pno}`)
                .then(resp => {
                  data[i].comments = resp.data;
                  data[i].msg = "";
                })
            );

            promises.push(
              axios
                .get(`http://localhost:8080/like/${data[i].pno}`)
                .then(resp => {
                  data[i].like = resp.data;
                })
            );
          }

          Promise.all(promises)
            .then()
            .then(() => {
              if (data.length) {
                this.page += 1;
                this.posts.push(...data);
                console.log("------");
                console.log(data);
                $state.loaded();
              } else {
                $state.complete();
              }
            });
        })
        .catch(err => {
          console.log(err);
        });
    },
  },
  components: {
  }
};
</script>

<style scoped>
.container {
  max-width: 840px;
}
img {
  width: 100%;
  height: 80%;
  margin: auto;
  /* max-width: 300px; */
}
.home {
  background-color: #fafafa;
}
.form-control {
  border: 0;
}
.form-control:disabled,
.form-control[readonly] {
  background-color: white;
  opacity: 1;
}
.lcnt {
  display: inline-block;
  font-weight: 400;
  color: #212529;
  text-align: center;
  vertical-align: middle;
  user-select: none;
  background-color: transparent;
  border: 1px solid transparent;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  line-height: 1.5;
  border-radius: 0.25rem;
  transition: color 0.15s;
}
.btn:focus,
.btn:active {
  outline: none !important;
  box-shadow: none;
}
.logo {
  width: 100px;
  height: 50px;
}
button {
  background: none;
  border: none;
  padding: 0;
  outline: inherit;
  cursor: pointer;
}
button:focus {
  outline: none !important;
}
</style>