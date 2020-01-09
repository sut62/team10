<template>
  <v-app>
  <div class="bg">  
     <v-content>
      <router-view />
    </v-content>
    </div> 
  </v-app>
</template>
<script>
  export default {
  name: 'App',

  methods: { 
    vaccineinformation(){
      this.$router.push("/vaccineinformation");
    },
    },
};

</script>