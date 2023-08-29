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
      <p>Feel free to contact me about this project or developer positions at asuver4@gmail.com</p> <br>
      <div id="links">
      <a href="https://github.com/adam-suver" target="_blank"><img src="../images/github.png" alt="GitHub logo" id="github-link"/></a> 
      <a href="https://www.linkedin.com/in/adam-suver/" target="_blank"><img src="../images/linkedin.png" alt="LinkedIn logo" id="linkedin-logo"/></a>
      </div>
      <img src="../images/headshot.jpg" alt="Adam Suver Headshot" id="headshot"/>
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
  background-image: url('../images/court.jpg');
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center center;
  align-items: center;
  justify-content: center;
  font-family: Calibri, Geneva, Tahoma, sans-serif;
}
p {
  color: white;
  font-weight: bold;
}

h2 {
  color: white;
  margin: auto;
}

#about-proj {
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin: 60px;
}

#about-dev {
  display: flex;
  flex-direction: column;
  margin: 60px;
  justify-content: center;
  align-items: center;
}

#headshot {
  border-radius: 50%;
  height: 325px;
}

#links {
  display: flex;
  align-items: center;
  margin: 15px;
}

#github-link, #linkedin-logo {
  width: 100px;
  display: inline;
  margin: 25px;
}

</style>