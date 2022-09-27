class Youtube {
  constructor(httpClient) {
    this.youtube = httpClient;
  }

  // async workout() {
  //   const response = await this.youtube.get('search', {
  //     params: {
  //       part: 'snippet',
  //       maxResults: 5,
  //       type: 'video',
  //       q: '운동',
  //       videoDefinition: 'high',
  //       order: 'viewCount',
  //     },
  //   });
  //   // return response.data.items.id.videoId;
  //   return response.data.items;
  // }

  // , contentDetails, statistics
  async videoData(id) {
    const response = await this.youtube.get('videos', {
      params: {
        part: 'snippet',
        id: id,
      },
    });
    return response.data.items;
  }

  async search(query) {
    const response = await this.youtube.get('search', {
      params: {
        part: 'snippet',
        maxResults: 2,
        type: 'video',
        q: query,
        videoDefinition: 'high',
      },
    });
    return response.data.items.map((item) => ({
      ...item,
      id: item.id.videoId,
    }));
  }
}

export default Youtube;
