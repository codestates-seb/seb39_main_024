import axios from 'axios';
// import https from 'https';

const instance = axios.create({
  baseURL: `https://server.main024.shop/`,
});

export default instance;
