<template>
  <v-flex>
    <v-text-field
      v-model="keyword"
      label="Search Cafe, User .."
      solo
      clearable
      @keypress.enter="searchCafeUser(keyword)"
    ></v-text-field>
    <v-tabs>
      <v-tab>Cafe</v-tab>
      <v-tab>User</v-tab>
      <v-spacer></v-spacer>
      <v-tab>카페 전체보기</v-tab>
      <v-tab-item>
        <CafeSearchList/>
      </v-tab-item>
      <v-tab-item>
        <UserSearchList/>
      </v-tab-item>
      <v-tab-item>
        <CafeAllList/>
      </v-tab-item>
    </v-tabs>
  </v-flex>
</template>

<script>
import { mapActions } from 'vuex'
import CafeAllList from '@/components/search/CafeAllList.vue'
import CafeSearchList from '@/components/search/CafeSearchList.vue'
import UserSearchList from '@/components/search/UserSearchList.vue'

export default {
  name: 'CafeListView',
  data() {
    return {
      keyword: null,
    }
  },
  components: {
    CafeAllList,
    CafeSearchList,
    UserSearchList,
  },
  methods: {
    ...mapActions([
      'searchCafeUser',
    ]),
  },
  created() {
    this.searchCafeUser(this.keyword)
  }
}
</script>

<style scoped>
.hover-table :hover {
  cursor: pointer;
}

</style>