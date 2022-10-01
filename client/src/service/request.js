import axios from 'axios';

const instance = axios.create({
  baseURL: `http://211.41.205.19:8080`,
});

export default instance;
