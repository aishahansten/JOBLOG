const hanspell = require('hanspell');
const chalk = require('chalk');
const minimatch = require('minimatch');

//맞춤법 검사할 문장
let sentence = '외않되';

const typomap = new Map();

const lookaround = (() => {
    try {
      RegExp('(?<=[^ㄱ-ㅎㅏ-ㅣ가-힣])test(?=[^ㄱ-ㅎㅏ-ㅣ가-힣])');
      return true;
    } catch (err) {
      return false;
    }
})();
  
let ignores;
  
function fixTypo(input, output) {
    if (ignores && minimatch(input, ignores)) {
      return;
    }
  
    try {
      if (input !== output.suggestions[0]) {
        let escaped = input.replace(/[-/\\^$*+?.()|[\]{}]/g, '\\$&');
        if (lookaround) {
          escaped = `(^|(?<=[^ㄱ-ㅎㅏ-ㅣ가-힣]))${escaped}((?=[^ㄱ-ㅎㅏ-ㅣ가-힣])|$)`;
        }
        sentence = sentence.replace(
          new RegExp(escaped, 'g'),
          chalk.inverse(output.suggestions[0]),
        );
      }
    } catch (err) {
      return;
    }
}
  
function fixTypos(data) {
    data.forEach((typo) => {
        if (typomap.get(typo.token) == null) {
        fixTypo(typo.token, typo);
        typomap.set(typo.token, true);
        }
    });
}
  
const HTTP_TIMEOUT = 20000;

let result = '';
  
function printSentence() {
    result += sentence;
    if (sentence[sentence.length - 1] !== '\n') {
        result += '\n';
    }
    //맞춤법 검사 결과
    console.log(result);
}

hanspell.spellCheckByPNU(sentence, HTTP_TIMEOUT, fixTypos, printSentence);
