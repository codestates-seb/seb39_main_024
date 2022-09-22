import { useRecoilValue } from 'recoil';

import { postsAllState } from '../../recoil/posts/postsAllState';
import One from './One';

export default function All() {
  const posts = useRecoilValue(postsAllState);

  return (
    <div>
      {posts.map((post) => (
        <One key={post.boardId} items={post} />
      ))}
    </div>
  );
}
