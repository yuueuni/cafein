<template>
  <v-list-item>
    <v-list-item-content>
      <v-list-item-title>Cafe keywords</v-list-item-title>
      <v-list-item-subtitle>
        <v-chip-group
          active-class="deep-purple--text text--accent-4"
          mandatory
        >
          <v-chip disabled>키워드1</v-chip>
          <v-chip disabled>키워드2</v-chip>
          <v-chip disabled>키워드3</v-chip>
        </v-chip-group>
      </v-list-item-subtitle>
    </v-list-item-content>
  </v-list-item>
</template>

<script>
export default {
  name: 'PostList',

}
</script>

<style>

</style>