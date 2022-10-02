import { selector } from 'recoil';
import axios from 'axios';

import { categoryIdState } from '../atoms/categoryIdState';

export const postsCategoryState = selector({
  key: 'postsCategoryState',
  get: async ({ get }) => {
    const categoryId = get(categoryIdState);
    const res = await axios.get(
      `https://211.41.205.19:8080/board?categoryId=${categoryId}&sort=id,DESC`
    );
    const data = await res.data.data;
    return data;
  },
});
