import { selectorFamily } from 'recoil';
import instance from '../../service/request';

export const postsAllState = selectorFamily({
  key: 'postsAllState',
  get: (page) => async () => {
    const res = await instance.get(`/board/all?page=${page}&size=2`);
    const data = await res.data.data;
    return data;
  },
});
