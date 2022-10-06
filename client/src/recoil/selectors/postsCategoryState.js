import { selectorFamily } from 'recoil';
import instance from '../../service/request';

import { categoryIdState } from '../atoms/categoryIdState';
import { authorizationState } from '../atoms/authorizationState';

export const postsCategoryState = selectorFamily({
  key: 'postsCategoryState',
  get:
    (page) =>
    async ({ get }) => {
      try {
        const categoryId = get(categoryIdState);
        const token = get(authorizationState);
        const res = await instance.get(
          `/board?categoryId=${categoryId}&page=${page}&size=4&sort=id,DESC`,
          {
            headers: {
              'Content-Type': 'application/json',
              Authorization: token,
            },
          }
        );
        const data = await res.data;
        return data;
      } catch (err) {
        console.log('err', err);
      }
    },
});
