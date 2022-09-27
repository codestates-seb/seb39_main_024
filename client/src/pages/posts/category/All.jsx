import { useRecoilValue } from 'recoil';

import { postsAllState } from '../../../recoil/selectors/postsAllState';
import Post from '../common/Post';

export default function All() {
  const posts = useRecoilValue(postsAllState);

  return (
    <div className="flex flex-wrap justify-center items-start bg-pink">
      {posts.map((post) => (
        <Post key={post.boardId} items={post} />
      ))}
    </div>
  );
}
