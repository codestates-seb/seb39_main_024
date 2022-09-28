import { Suspense, lazy } from 'react';
import { Route, Routes } from 'react-router-dom';
import { RecoilRoot } from 'recoil';

const SearchResult = lazy(() => import('./pages/videos/SearchResult'));
const Topbar = lazy(() => import('./containers/Topbar'));
const Navbar = lazy(() => import('./containers/Navbar'));
const Join = lazy(() => import('./pages/auth/Join'));
const Login = lazy(() => import('./pages/auth/Login'));
const Layout = lazy(() => import('./containers/Layout'));
const Home = lazy(() => import('./pages/Home'));
const VideosALL = lazy(() => import('./pages/videos/VideosAll'));
const Popularity = lazy(() => import('./pages/videos/Popularity'));
const Training = lazy(() => import('./pages/videos/Training'));
const Stretching = lazy(() => import('./pages/videos/Stretching'));
const VideoDetail = lazy(() => import('./pages/videos/VideoDetail'));
const PostsAll = lazy(() => import('./pages/posts/category/All'));
const Record = lazy(() => import('./pages/posts/category/Record'));
const Free = lazy(() => import('./pages/posts/category/Free'));
const Meal = lazy(() => import('./pages/posts/category/Meal'));
const Read = lazy(() => import('./pages/posts/common/Read'));
const Create = lazy(() => import('./pages/posts/create/Create'));
const Edit = lazy(() => import('./pages/mypage/ProfileEdit'));
const MyPage = lazy(() => import('./pages/mypage/MyPage'));
const Calendar = lazy(() => import('./pages/mypage/Calendar'));
const ProfileEdit = lazy(() => import('./pages/mypage/ProfileEdit'));
const DeleteAccount = lazy(() => import('./pages/mypage/DeleteAccount'));
const Footer = lazy(() => import('./containers/Footer'));

function App() {
  return (
    <RecoilRoot>
      <Suspense fallback={<p>로딩중</p>}>
        <Topbar />
        <Navbar />
        <Layout>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/join" element={<Join />} />
            <Route path="/videos" element={<VideosALL />} />
            <Route path="/videos/popularity" element={<Popularity />} />
            <Route path="/videos/training" element={<Training />} />
            <Route path="/videos/stretching" element={<Stretching />} />
            <Route path="/videos/search-result" element={<SearchResult />} />
            <Route path="/videos/detail" element={<VideoDetail />} />
            <Route path="/posts" element={<PostsAll />} />
            <Route path="/posts/free" element={<Free />} />
            <Route path="/posts/meal" element={<Meal />} />
            <Route path="/posts/record" element={<Record />} />
            <Route path="/posts/edit" element={<Create />} />
            <Route path="/posts/edit" element={<Edit />} />
            <Route path="/posts/create" element={<Create />} />
            <Route path="/posts/read" element={<Read />} />
            <Route path="/mypage" element={<MyPage />} />
            <Route path="/mypage/calendar" element={<Calendar />} />
            <Route path="/mypage/profile_edit" element={<ProfileEdit />} />
            <Route path="/mypage/deleteaccount" element={<DeleteAccount />} />
          </Routes>
        </Layout>
        <Footer />
      </Suspense>
    </RecoilRoot>
  );
}

export default App;
