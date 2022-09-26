import { atom } from 'recoil';
import { recoilPersist } from 'recoil-persist';

const { persistAtom } = recoilPersist();

//videos
// export const videosState = atom({
//   key: 'videosState',
//   default: [],
//   effects_UNSTABLE: [persistAtom],
// });

export const selectedVideoState = atom({
  key: 'selectedVideoState',
  default: [],
  effects_UNSTABLE: [persistAtom],
});
