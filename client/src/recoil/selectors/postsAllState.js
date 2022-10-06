import { selectorFamily } from 'recoil';
import instance from '../../service/request';

export const postsAllState = selectorFamily({
  key: 'postsAllState',
  get: (page) => async () => {
    try {
      const res = await instance.get(`/board/all?page=${page}&size=4`);
      const data = await res.data;
      return data;
    } catch (err) {
      console.log('err', err);
    }
  },
});
