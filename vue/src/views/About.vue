<template>
  <div id="about">
    <div id="about-proj">
      <h2>About This Project</h2>
      <p>This is a side project I built to be useful to anyone who wants to research NBA prop bets without combing through game logs,
         at least until we have an idea of what player and statistical category would be a promising bet.</p>
      <p>The backend is coded in Java and utilizes Spring to connect to the NBA stats API at balldontlie.io,
         which I appreciate for being one of the few free statistics APIs out there. It does come with a rate limit,
          so if your request does not go through the first time, try again in a minute or two.</p>
      <p>The frontend consists of JavaScript, Vue, HTML, and CSS.</p>
      <p>The database, written in PostgreSQL, only stores the unique player IDs from the API, and I will have to continually
         update it with new players—as well as some current ones who are not yet in the database.
          I will be adding them, but feel free to let me know about any particulars that you notice are missing.</p>
    </div>
    <div id="about-dev">
      <h2>About The Developer</h2>
      <p>My name is Adam Suver, and I am a graduate of Tech Elevator’s Java Web Application Development bootcamp. 
        I live in Columbus, Ohio, and I am currently open to software developer opportunities.</p>
      <p>When I am not coding, I enjoy playing board games and tabletop roleplaying games like Dungeons & Dragons,
         venturing out to craft breweries, and playing pickleball with my friends.</p>
      <p>Feel free to contact me about this project or developer positions at asuver4@gmail.com</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "about",

}
</script>

<style scoped>
#about {
  display: flex;
  background-color: black;
}
p {
  color: white;
}

h2 {
  color: white;
}

#about-proj {
  margin: 75px;
}

#about-dev {
  margin: 75px;
}

</style>