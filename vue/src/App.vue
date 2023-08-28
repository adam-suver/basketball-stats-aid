<template>
  <div id="app">
    <div id="nav">
    <router-link id="home-link" v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link id="about-link" v-bind:to="{ name: 'about' }">About</router-link>
    </div>
    <router-view />
  </div>
</template>
<style scoped>
#nav {
  background-color: black;
  color: lightblue;
}

#home-link, #about-link {
  color: lightsteelblue;
}

</style>
