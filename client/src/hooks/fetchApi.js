import axios from 'axios';

export const GetData = async (url, setState) => {
  try {
    await axios.get(url).then(res => {
      setState(res.data);
    });
    return 'success';
  } catch (e) {
    console.log(e.message);
    return 'fail';
  }
};
