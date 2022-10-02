import { selectorFamily } from 'recoil';
import axios from 'axios';

export const postsAllState = selectorFamily({
  key: 'postsAllState',
  get: (page) => async () => {
    const res = await axios.get(
      `http://211.41.205.19:8080/board/all?page=${page}&size=2`
    );
    const data = await res.data.data;
    return data;
  },
});
