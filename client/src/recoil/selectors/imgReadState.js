import { selectorFamily } from 'recoil';
import instance from '../../service/request';

export const imgReadState = selectorFamily({
  key: 'imgReadState',
  get: (imageId) => async () => {
    const res = await instance.get(`/board/image/${imageId}`);
    const data = await res.data;
    return data;
  },
});
