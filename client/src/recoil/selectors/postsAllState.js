import { selector } from 'recoil';
import axios from 'axios';

export const postsAllState = selector({
  key: 'postsAllState',
  get: async () => {
    const res = await axios.get('http://211.41.205.19:8080/board/all');
    const data = await res.data.data;
    return data;
  },
});
