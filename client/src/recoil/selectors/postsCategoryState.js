import { selectorFamily } from 'recoil';
import instance from '../../service/request';

import { categoryIdState } from '../atoms/categoryIdState';

export const postsCategoryState = selectorFamily({
  key: 'postsCategoryState',
  get:
    (page) =>
    async ({ get }) => {
      try {
        const categoryId = get(categoryIdState);
        const res = await instance.get(
          // `http://211.41.205.19:8080/board?categoryId=${categoryId}&sort=id,DESC?page=${page}&size=2`
          `/board?categoryId=${categoryId}&page=${page}&size=4&sort=id,DESC`
        );
        const data = await res.data.data;
        return data;
      } catch (err) {
        // if (err.response.status === 403) {
        //   alert(err.response.data);
        //   window.location.replace('/posts');
        // }
        console.log('err', err);
      }
    },
});
