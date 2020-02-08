<template>
  <v-card class="mx-auto" max-width="75%" tile>
    <v-toolbar       
      dense
      dark
      prominent           
      src="https://cdn.pixabay.com/photo/2013/02/21/19/12/charcoal-84670_1280.jpg" class="mx-auto mb-1">  
      <v-toolbar-title><h1>ระบบจัดการโรคติดต่อ</h1></v-toolbar-title>
      <v-spacer></v-spacer>   
      
      <v-icon @click="Logout" class="font-weight-black">Logout</v-icon>   
    </v-toolbar>

      <v-img
        src="https://cdn.pixabay.com/photo/2014/12/10/21/01/doctor-563428_1280.jpg" class="mx-auto mb-1">
      
      <v-row
        align="fill-end"
        class="fill-height"
      >
      <bar />
      <v-col
        align-self="start"
        class="pa"
        cols="0"
      >    
      </v-col>
      </v-row>
    </v-img>
  </v-card>
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