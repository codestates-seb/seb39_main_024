import { selector } from 'recoil';
import { memberIdState } from '../atoms/memberIdState';
import instance from '../../service/request';

export const getWorkoutTimeState = selector({
  key: 'getWorkoutTimeState',
  get: async ({ get }) => {
    try {
      const memberId = get(memberIdState);
      const data = await (await instance.get(`/members/${memberId}`)).data;
      return data;
    } catch (err) {
      console.log('err', err);
    }
  },
});
