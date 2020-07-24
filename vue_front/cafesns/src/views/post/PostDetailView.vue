<template>
  <div>
    <v-btn @click="$router.go(-1)">뒤로</v-btn>
    <v-row justify="center">
      <v-col cols="12" sm="10" md="8" lg="6">
        <v-card ref="form" class="d-inline-block px-3 pb-3">
          <v-card-text class="text-center">
            <v-card-title>
              <h1>Cafe name</h1>
              <v-spacer></v-spacer>
              <span class="text-subtitle-2">user</span>
            </v-card-title>
            <v-divider></v-divider>

            <!-- taste -->
            <v-btn-toggle
              tile
              color="deep-purple accent-3"
              group
              mandatory
              class="post-checklist"
            >
              <v-btn depressed class="disabled-button">
                <v-icon left>mdi-thumb-up</v-icon>
                <span>like</span>
              </v-btn>
              <v-btn depressed class="disabled-button">
                <span>맛</span>
              </v-btn>
              <v-btn depressed class="disabled-button">
                <v-icon left>mdi-thumb-down</v-icon>
                <span>unlike</span>
              </v-btn>
            </v-btn-toggle>

            <!-- mood -->
            <v-btn-toggle
              tile
              color="deep-purple accent-3"
              group
              mandatory
              class="post-checklist"
            >
              <v-btn depressed class="disabled-button">
                <v-icon left>mdi-thumb-up</v-icon>
                <span>like</span>
              </v-btn>
              <v-btn depressed class="disabled-button">
                <span>분위기</span>
              </v-btn>
              <v-btn depressed class="disabled-button">
                <v-icon left>mdi-thumb-down</v-icon>
                <span>unlike</span>
              </v-btn>
            </v-btn-toggle>

            <!-- clean -->
            <v-btn-toggle
              tile
              color="deep-purple accent-3"
              group
              mandatory
              class="post-checklist"
            >
              <v-btn depressed class="disabled-button">
                <v-icon left>mdi-thumb-up</v-icon>
                <span>like</span>
              </v-btn>
              <v-btn depressed class="disabled-button">
                <span>위생</span>
              </v-btn>
              <v-btn depressed class="disabled-button">
                <v-icon left>mdi-thumb-down</v-icon>
                <span>unlike</span>
              </v-btn>
            </v-btn-toggle>

            <v-textarea
              label="Content" 
              disabled
              placeholder="(유저 작성 글) Lorem ipsum dolor sit amet, consectetur adipisicing elit. Harum maiores modi quidem veniam, expedita quis laboriosam, ullam facere adipisci, iusto, voluptate sapiente corrupti asperiores rem nemo numquam fuga ab at."
            >
            </v-textarea>

            <div style="height:200px; border:1px solid black">
              사진
            </div>
            <v-img contain max-width="100%" max-height="300px"></v-img>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn text color="secondary">삭제</v-btn>
            <v-btn text color="primary">수정</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  name: 'PostDatilView',
}
</script>

<style scoped>
.disabled-button {
  pointer-events: none;
}

</style>