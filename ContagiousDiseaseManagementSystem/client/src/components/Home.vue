<template>
  <v-app>
    <div class="mx-auto text-center" style="width: 100%;">
      <v-toolbar>
        <v-toolbar-title>
          <h1>ระบบจัดการโรคติดต่อ</h1>
        </v-toolbar-title>
        <v-spacer />
        <v-tab @click="Logout">Logout</v-tab>
      </v-toolbar>
      <br />
      <v-layout text-center wrap>
        <bar />
      </v-layout>
    </div>
  </v-app>
</template>

<script>
import bar from "../components/bar";
export default {
  components: {
    bar
  },

  methods: {
    Logout() {
      alert("Logout !!!");
      this.$router.push("/");
    }
  }
};
</script>