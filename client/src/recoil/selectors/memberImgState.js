import { selector } from 'recoil';
import instance from '../../service/request';
import { memberIdState } from '../atoms/memberIdState';

export const memberImgState = selector({
  key: 'memberImgState',
  get: async ({ get }) => {
    const memberId = get(memberIdState);
    const res = await instance.get(`/members/${memberId}/image`);
    const data = await res.data;
    return data;
  },
});
