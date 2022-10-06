import { selectorFamily } from 'recoil';
import instance from '../../service/request';

import { authorizationState } from '../atoms/authorizationState';

export const imgReadState = selectorFamily({
  key: 'imgReadState',
  get:
    (imageId) =>
    async ({ get }) => {
      try {
        const token = get(authorizationState);
        const res = await instance.get(`/board/image/${imageId}`, {
          headers: {
            'Content-Type': 'application/json',
            Authorization: token,
          },
        });
        const data = await res.data;
        return data;
      } catch (err) {
        console.log('err', err);
      }
    },
});
