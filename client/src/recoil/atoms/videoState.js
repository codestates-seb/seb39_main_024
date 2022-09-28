import { atom } from 'recoil';
import { recoilPersist } from 'recoil-persist';

const { persistAtom } = recoilPersist();

// search result store
// export const videosState = atom({
//   key: 'videosState',
//   default: [],
//   effects_UNSTABLE: [persistAtom],
// });

// video detail data store
export const selectedVideoState = atom({
  key: 'selectedVideoState',
  default: [],
  effects_UNSTABLE: [persistAtom],
});
