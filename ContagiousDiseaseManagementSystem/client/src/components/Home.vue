<template>
  <v-app>
    <v-card class="mx-auto" tile max-width="2000">
      <v-layout text-center wrap>
        <v-flex mb-4>
          <br />
          <bar />
          <h1 class="display-2 font-weight-bold mb-3">ระบบจัดการโรคติดต่อ</h1>
        </v-flex>
      </v-layout>
    </v-card>
  </v-app>
</template>

<script>
import bar from '../components/bar' ;
export default {
  components: {
    bar
  }
}
</script>