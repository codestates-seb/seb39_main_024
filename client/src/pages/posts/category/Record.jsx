import { useRecoilValue } from 'recoil';

import { postsCategoryState } from '../../../recoil/selectors/postsCategoryState';
import Post from '../common/Post';

export default function Record() {
  const posts = useRecoilValue(postsCategoryState);

  return (
    <div className="flex flex-wrap justify-center items-start bg-pink">
      {posts.map((post) => (
        <Post key={post.boardId} items={post} />
      ))}
    </div>
  );
}
