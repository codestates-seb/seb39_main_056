import axios from 'axios';

export const GetData = async (url, setState, headers) => {
  try {
    await axios.get(url, { headers }).then(res => {
      if (400 < res.status && res.status < 600) throw new Error('에러');
      setState(res.data);
    });
    return 'success';
  } catch (e) {
    console.log(e.message);
    return 'fail';
  }
};

export const PostData = async (url, headers, data) => {
  try {
    await axios
      .post(url, data, {
        headers,
      })
      .then(response => {
        if (400 < response.status && response.status < 600) {
          throw new Error('No Response');
        }
      });
    return 'success';
  } catch (e) {
    console.log(e.message);
    return 'fail';
  }
};
