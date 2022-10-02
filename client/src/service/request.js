import axios from 'axios';
// import https from 'https';

const instance = axios.create({
  baseURL: `https://211.41.205.19:8080`,
});

export default instance;
